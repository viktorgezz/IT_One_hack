from fastapi import FastAPI, Response
from fastapi.responses import FileResponse
from getJSON_and_mk_list import GetJSONAndMakeList
from make_chart import MakeChart

app = FastAPI()

@app.get("/image/{id_account}")
async def get_image(id_account: int):
    getJSON = GetJSONAndMakeList(id_account)
    data_list = getJSON.JSON_to_dict()
    make_chart = MakeChart(data_list[0], data_list[1])
    file_path = 'pie_chart.png'
    return FileResponse(file_path, media_type='image/png', filename='image.png')