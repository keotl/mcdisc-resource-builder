from mcdiscbuilder import downloader
from mcdiscbuilder.metadata import prepare_lang_file, prepare_sounds_json, prepare_pack_info
from mcdiscbuilder.disc_list import get_discs
from mcdiscbuilder.archive import create_zip


def build_resource_pack(disc_list_location: str, temporary_build_directory: str, zip_file_destination_directory: str):
    discs = get_discs(disc_list_location)

    prepare_lang_file(discs, temporary_build_directory + "/assets/mcdisc/lang/en_us.lang")
    prepare_sounds_json(discs, temporary_build_directory + "/assets/mcdisc/sounds.json")
    prepare_pack_info(temporary_build_directory + "/pack.mcmeta")

    for disc in discs:
        downloader.download(disc['url'], temporary_build_directory + "/assets/mcdisc/sounds/" + disc['soundId'])

    create_zip(temporary_build_directory, zip_file_destination_directory)
