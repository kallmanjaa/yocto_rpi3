SECTION = "kernel"

DESCRIPTION = "Linux kernel for Raspberry pi 3 devices"

LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"


DEPENDS += "openssl-native openssl bc-native autoconf rsync-native kmod"

S = "${WORKDIR}/git"
#B = "${WORKDIR}/build"


SRCBRANCH = "rpi-5.5.y"
LOCALVERSION = "-${SRCBRANCH}"

KERNEL_SRC ?= "git://github.com/raspberrypi/linux.git;protocol=https"
SRC_URI = "${KERNEL_SRC};branch=${SRCBRANCH}"

SRCREV = "${AUTOREV}"

export CROSS_COMPILE="${TARGET_PREFIX}" 
export CC="${TARGET_PREFIX}gcc"
export HOSTCC="${BUILD_CC}" 

# Improve code quality.
EXTRA_OEMAKE += 'KCFLAGS="-Werror"'
export CFLAGS += "-m32"
export ARCH = "arm"

do_compile() {
	make  -j4 -C ${S} O=${B} clean
	make  -j4 -C ${S} O=${B} bcm2709_defconfig
	make  -j4 -C ${S} O=${B} zImage modules dtbs
}


INSANE_SKIP_${PN} += "arch"
INSANE_SKIP_${PN}-dbg += "arch"

do_install() {
	install -d ${DEPLOY_DIR_IMAGE}/boot_partition/overlays
	install -d ${D}${base_libdir}
	install -d ${D}${includedir}
	make -j4 -C ${B} modules_install INSTALL_MOD_PATH="${D}"
	make -j4 -C ${B} headers_install INSTALL_HDR_PATH="${D}/usr"	

	cp ${B}/arch/arm/boot/zImage ${DEPLOY_DIR_IMAGE}/boot_partition/kernel.img 
	cp ${B}/arch/arm/boot/dts/bcm2710-rpi-3-b.dtb ${DEPLOY_DIR_IMAGE}/boot_partition
	cp ${B}/arch/arm/boot/dts/overlays/*.dtbo ${DEPLOY_DIR_IMAGE}/boot_partition/overlays
	
	rm -f "${D}${base_libdir}/modules/5.5.19-v7+/build"
	rm -f "${D}${base_libdir}/modules/5.5.19-v7+/source"
}

PACKAGES = "${PN}"

FILES_${PN} += " /usr \
		/lib \
		"
		
do_package_qa[noexec] = "1"
