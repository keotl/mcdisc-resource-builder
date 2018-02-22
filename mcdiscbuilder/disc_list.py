import json

import requests


def get_discs(path: str) -> list:
    if path.startswith("http://") or path.startswith("https://") or path.startswith("ftp://"):
        return requests.get(path).json()
    else:
        with open(path, 'r') as file:
            return json.load(file)
