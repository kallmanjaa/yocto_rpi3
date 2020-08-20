SECTION = "boot"

DESCRIPTION = "Boot partition for rpi3 devices"

LICENSE = "GPLv3"

LIC_FILES_CHKSUM = "file://${WORKDIR}/git/boot/LICENCE.broadcom;md5=c403841ff2837657b2ed8e5bb474ac8d"

SRC_URI = "file://files/config.txt"
SRC_URI += " file://files/cmdline.txt"
SRC_URI = "git://github.com/raspberrypi/firmware.git;protocol=http"

SRC_URI[Licence.md5sum] = "c403841ff2837657b2ed8e5bb474ac8d"
SRC_URI[Licence.sha256sum] = "c7283ff51f863d93a275c66e3b4cb08021a5dd4d8c1e7acc47d872fbe52d3d6b"

SRC_URI[startelf.md5sum] = "32aa2cb8aa145e138a693283c1f49ce5"
SRC_URI[startelf.sha256sum] = "2b508107378fcb8ee74ce61aa9bf118f09817e6d11ab9135b3f61d769e1e9a68"

SRC_URI[bootcode.md5sum] = "100e8e2521ffb0f2bb87ae9c2d404997"
SRC_URI[bootcode.sha256sum] = "12c6b5fdd893ff60cddbad0fa8aea0ebd5328ed2a9cd39a2a09d7ac99621d5bf"

SRC_URI[fixup.md5sum] = "75ea1fdc4dee1b9ee44e0ce71d1e97fe"
SRC_URI[fixup.sha256sum] = "d3907e7d5ca18fc3ece278e10cd3f6ac34b2b92dc2046c5c98cdc06359a38984"

SRCREV = "${AUTOREV}"


do_install() {
	install -d ${DEPLOY_DIR_IMAGE}/boot_partition

	cp ${THISDIR}/files/config.txt ${DEPLOY_DIR_IMAGE}/boot_partition
	cp ${THISDIR}/files/cmdline.txt ${DEPLOY_DIR_IMAGE}/boot_partition
	cp ${WORKDIR}/git/boot/start.elf ${DEPLOY_DIR_IMAGE}/boot_partition
	cp ${WORKDIR}/git/boot/bootcode.bin ${DEPLOY_DIR_IMAGE}/boot_partition
	cp ${WORKDIR}/git/boot/fixup.dat ${DEPLOY_DIR_IMAGE}/boot_partition
}

