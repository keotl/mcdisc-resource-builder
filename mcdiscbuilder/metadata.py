import json


def prepare_sounds_json(discs: list, destination: str):
    sounds = {}
    for disc in discs:
        sounds[disc["soundId"]] = {"category": "block", "sounds": [
            {"name": "mcdisc:" + disc["soundId"],
             "stream": True}]}
    with open(destination, 'w') as jsonFile:
        json.dump(sounds, jsonFile)


def prepare_lang_file(discs: list, destination: str):
    lines = []
    for disc in discs:
        lines.append("item.record.{}.desc={} - {}".format(disc['minecraftId'], disc['artist'], disc['name']))
    with open(destination, 'w') as langFile:
        langFile.writelines(lines)
