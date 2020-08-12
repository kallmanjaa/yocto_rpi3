SECTION = "boot"

DESCRIPTION = "Boot partition for rpi3 devices"

LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"


SRC_URI += " file://config.txt"
SRC_URI += " file://cmdline.txt"
SRC_URI += " https://github.com/raspberrypi/firmware/raw/master/boot/start.elf;name=startelf"
SRC_URI += " https://github.com/raspberrypi/firmware/raw/master/boot/bootcode.bin;name=bootcode"
SRC_URI += " https://github.com/raspberrypi/firmware/raw/master/boot/fixup.dat;name=fixup"


SRC_URI[startelf.md5sum] = "8c2b2d16668b33fc4f7d006c2fe3af35"
SRC_URI[startelf.sha256sum] = "c9fa3250b4f051579a7763d955c9575fe56acde11a66d3351263fcdccfa8a0b9"

SRC_URI[bootcode.md5sum] = "100e8e2521ffb0f2bb87ae9c2d404997"
SRC_URI[bootcode.sha256sum] = "12c6b5fdd893ff60cddbad0fa8aea0ebd5328ed2a9cd39a2a09d7ac99621d5bf"

SRC_URI[fixup.md5sum] = "e732f934ba808941f186cfc2fc8ed90e"
SRC_URI[fixup.sha256sum] = "cf3ad9be62f5f9627f39d8167245804fbbd505660bdf1291473c4f52e4e693da"

SRCREV = "${AUTOREV}"


do_install() {
	install -d ${DEPLOY_DIR_IMAGE}/boot_partition

	cp ${WORKDIR}/config.txt ${DEPLOY_DIR_IMAGE}/boot_partition
	cp ${WORKDIR}/cmdline.txt ${DEPLOY_DIR_IMAGE}/boot_partition
	cp ${WORKDIR}/start.elf ${DEPLOY_DIR_IMAGE}/boot_partition
	cp ${WORKDIR}/bootcode.bin ${DEPLOY_DIR_IMAGE}/boot_partition
	cp ${WORKDIR}/fixup.dat ${DEPLOY_DIR_IMAGE}/boot_partition
}

