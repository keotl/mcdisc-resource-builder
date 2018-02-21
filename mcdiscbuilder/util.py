import requests


def get_json(url: str) -> dict:
    return requests.get(url).json()
