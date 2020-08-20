SECTION = "QT5"

DESCRIPTION = "QT5-controls for RPI3 devices"

LICENSE = "GPL-2.0+"

LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/qt/qtquickcontrols.git;branch=5.13.2;protocol=http"

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

DEPENDS += "zlib-native zlib libpng-native libpng dbus dbus-native libdrm egl qt5-base qt5-declarative"

SYSROOT_DIRS += "/qt"

do_configure(){
	
	${STAGING_DIR_TARGET}/qt/bin/qmake -makefile
}

INSANE_SKIP = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"
INHIBIT_SYSROOT_STRIP = "1"

do_install(){

	install -d ${D}
	CROSS_COMPILE=${TARGET_PREFIX} make install
	CROSS_COMPILE=${TARGET_PREFIX} make INSTALL_ROOT=${D} install
	find ${D} -type d -name "qt" -exec mv -t ${D} {} +
	find ${D} -type d -empty -delete

}

PACKAGES = "${PN}"

FILES_${PN} += " /qt \
		"

do_package_qa[noexec] = "1"
