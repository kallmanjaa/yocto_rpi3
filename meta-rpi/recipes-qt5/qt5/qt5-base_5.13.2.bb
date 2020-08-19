SECTION = "EGL"

DESCRIPTION = "EGL libs for RPI3 devices"

LICENSE = "GPL-2.0+"

LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/qt/qtbase.git;branch=5.13.2;protocol=http"

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

DEPENDS += "zlib-native zlib libpng-native libpng dbus dbus-native libdrm egl"

do_configure(){
		
	./configure \
		-opensource \
		-confirm-license \ 
		-prefix /qt \
		-release \
		-force-debug-info \
		-opengl es2 \
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

INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"
INHIBIT_PACKAGE_STRIP = "1"

do_install(){
	CROSS_COMPILE=${TARGET_PREFIX} make install
	install -d ${D}
	cp -R ${STAGING_DIR_TARGET}/qt ${D}
}

PACKAGES = "${PN}"

FILES_${PN} += " /qt \
		"

do_package_qa[noexec] = "1"
