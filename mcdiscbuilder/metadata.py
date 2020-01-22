import json
import os.path

from jivago.lang.stream import Stream


def prepare_sounds_json(discs: list, destination: str):
    sounds = {}
    for disc in discs:
        sounds[disc["soundId"]] = {"category": "block", "sounds": [
            {"name": "mcdisc:" + disc["soundId"],
             "stream": True}]}

    create_directory_if_not_exists(destination)

    with open(destination, 'w') as jsonFile:
        json.dump(sounds, jsonFile)


def prepare_lang_file(discs: list, destination: str):
    lines = Stream(discs) \
        .map(lambda disc: f"item.record.{disc['minecraftId']}.desc={disc['artist']} - {disc['name']}")

    create_directory_if_not_exists(destination)

    with open(destination, 'w') as langFile:
        langFile.write(os.linesep.join(lines))


def prepare_pack_info(destination: str):
    create_directory_if_not_exists(destination)
    with open(destination, 'w') as mcmeta:
        mcmeta.write('{"pack" : {"pack_format": 3,"description": "mcdisc resource pack"}}')


def create_directory_if_not_exists(destination):
    directory = os.path.dirname(destination)
    if not os.path.exists(directory):
        os.makedirs(directory)
