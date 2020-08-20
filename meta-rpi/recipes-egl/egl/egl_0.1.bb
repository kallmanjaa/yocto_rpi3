SECTION = "EGL"

DESCRIPTION = "EGL libs for RPI3 devices"

LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://${S}/../git/hardfp/opt/vc/LICENCE;md5=86e53f5f5909ee66900418028de11780"

SRC_URI = "git://github.com/raspberrypi/firmware.git;protocol=http"
SRC_URI += "file://eglext.patch"

SRCREV = "${AUTOREV}"

SRC_URI[md5sum] = "b71ea4d2e320b58cd345291c499e5466"
SRC_URI[sha256sum] = "e93d26b696c05ed75cdac84549947a539ed7a06becc59d585bc3d92bd7b9b538"

S = "${WORKDIR}/git"

SYSROOT_DIRS += "/opt"

do_patch(){
	
	patch -p1 ${S}/hardfp/opt/vc/include/EGL/eglext.h ${WORKDIR}/eglext.patch
}

do_install() {
	install -d ${D}
	cp -R ${S}/hardfp/* ${D}	
	rm -rf ${D}/opt/vc/src
}


PACKAGES = "${PN}"

FILES_${PN} += " /opt \
		"
do_package_qa[noexec] = "1"
