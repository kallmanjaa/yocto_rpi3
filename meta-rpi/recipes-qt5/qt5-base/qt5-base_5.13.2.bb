SECTION = "QT5"

DESCRIPTION = "QT5-base for RPI3 devices"

LICENSE = "GPL-2.0+"

LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/qt/qtbase.git;branch=5.13.2;protocol=http"

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

DEPENDS += "zlib-native zlib libpng-native libpng dbus dbus-native libdrm egl"

SYSROOT_DIRS += "/qt"

INSANE_SKIP = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"
INHIBIT_SYSROOT_STRIP = "1"

PACKAGES = "${PN}"

FILES_${PN} += " /qt \
		"

do_configure(){
		
	./configure \
		-opensource \
		-confirm-license \ 
		-release \
		-opengl es2 \
		-prefix /qt \
		-device linux-rasp-pi3-g++ \
		-sysroot ${STAGING_DIR_TARGET} \
		-device-option CROSS_COMPILE=${TARGET_PREFIX} \
		-no-xcb \
		-no-sql-db2 \
		-no-mtdev \
		-nomake tests \
		-nomake examples \
		-no-sql-mysql \
		-eglfs \
		-qpa eglfs \
		-make tools \
		-qt-pcre \
		-iconv \
		-no-xkb \
		-no-xkbcommon \
		-no-fontconfig \
		-no-kms \
		-system-libpng \
		-no-tslib \
		-no-icu \
		-no-directfb \
		-no-sql-oci \
		-no-sql-sqlite2 \
		-accessibility \
		-widgets \
		-linuxfb \
		-no-libudev \
		-no-sql-psql \
		-make libs \
		-no-openvg \
		-no-sql-tds \
		-dbus \
		-no-strip \
		-system-zlib \
		-no-sm \
		-no-sql-ibase \
		-no-sql-odbc \
		-release -v
}

do_install(){
	install -d ${D}
	make install
	make INSTALL_ROOT=${D} install
	mv ${D}${STAGING_DIR_TARGET}/qt ${D}
	find ${D} -type d -empty -delete
}

do_package_qa[noexec] = "1"
