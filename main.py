from mcdiscbuilder import downloader
from mcdiscbuilder.metadata import prepare_lang_file, prepare_sounds_json
from mcdiscbuilder.disc_list import get_discs

TEST_URL = "https://raw.githubusercontent.com/keotl/mcdisc/master/sample-disc-config.json"
TARGET_DIRECTORY = "/tmp/testmcdisc"

discs = get_discs(TEST_URL)

prepare_lang_file(discs, TARGET_DIRECTORY + "/assets/mcdisc/lang/en_us.lang")
prepare_sounds_json(discs, TARGET_DIRECTORY + "/assets/mcdisc/sounds.json")

for disc in discs:
    downloader.download(disc['url'], TARGET_DIRECTORY + "/assets/mcdisc/sounds/" + disc['soundId'])


