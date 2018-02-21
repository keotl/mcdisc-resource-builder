from mcdiscbuilder import downloader
from mcdiscbuilder.util import get_json

TEST_URL = "https://raw.githubusercontent.com/keotl/mcdisc/master/sample-disc-config.json"
TARGET_DIRECTORY = "/tmp/testmcdisc"

discs = get_json(TEST_URL)
for disc in discs:
    downloader.download(disc['url'], TARGET_DIRECTORY + "/assets/mcdisc/sounds/" + disc['soundId'])

