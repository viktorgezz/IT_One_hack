from dotenv import load_dotenv
import requests

class GetJSONAndMakeList(object):

    BASE_URL = ""

    def __init__(self, id_account):
        self.responce = requests.get(url=f"{self.BASE_URL}+/get_sum_categories/{id_account}").__dict__

    def JSON_to_dict(self):
        return [self.responce.keys(), self.responce.values()]
