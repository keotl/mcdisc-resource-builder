import requests


def get_json(url: str) -> list:
    return requests.get(url).json()
