package ru.viktorgezz.project1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.viktorgezz.project1.dto.FriendDTO;
import ru.viktorgezz.project1.repositories.AccountRepositories;

import java.util.List;

@Component
public class FriendDAO {

    private final JdbcTemplate jdbcTemplate;
    private final AccountRepositories accountRepositories;

    @Autowired
    public FriendDAO(JdbcTemplate jdbcTemplate, AccountRepositories accountRepositories) {
        this.jdbcTemplate = jdbcTemplate;
        this.accountRepositories = accountRepositories;
    }

    public void addFriend(FriendDTO friendDTO) {
        jdbcTemplate.update("INSERT INTO Friend(account1_id, account2_id, confirmed) VALUES (?, ?, ?)",
                friendDTO.getAccountId(), accountRepositories.findByLogin(friendDTO.getLogin()).getId(), false);
    }

    public void acceptFriend(FriendDTO friendDTO) {
        jdbcTemplate.update("UPDATE Friend SET confirmed = true WHERE " +
                "(account1_id = ? AND account2_id = ?) OR (account1_id = ? AND account2_id = ?)",
                friendDTO.getAccountId(), accountRepositories.findByLogin(friendDTO.getLogin()).getId(),
                accountRepositories.findByLogin(friendDTO.getLogin()).getId(), friendDTO.getAccountId()
                );
    }

    public List<String> getFriendRequests(int idAccount) {
        return jdbcTemplate.query("SELECT acc.login FROM ACCOUNT acc " +
                        "JOIN FRIEND fr ON fr.account1_id = acc.id " +
                        "WHERE fr.account2_id = ? AND fr.confirmed = FALSE",
                new Object[]{idAccount},
                (rs, rowNum) -> rs.getString("login"));
    }
}
