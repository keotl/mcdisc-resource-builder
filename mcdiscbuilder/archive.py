import shutil
import datetime


def create_zip(folder: str, destination_dir: str):
    shutil.make_archive(destination_dir + "/mcdisc-pack-{}".format(datetime.datetime.now().date()), "zip", folder)
