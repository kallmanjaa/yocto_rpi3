SECTION = "QT5"

DESCRIPTION = "QT5-base for RPI3 devices"

LICENSE = "GPL-2.0+"

LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/qt/qtbase.git;branch=5.13.2;protocol=http"

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = " \
    		MAKEFLAGS='${PARALLEL_MAKE}' \
    	       "
SYSROOT_DIRS += "/usr/local"

DEPENDS += "gperf-native \
	    gperf \
	    ninja-native \
	    ninja \
	    pkgconfig-native \
	    zlib-native \
	    zlib \
	    egl \
	    libpng \
	    libpng-native \
	    nspr-native \
	    nspr \
	    nss-native \
	    nasm-native \
	    bison-native \
	    libdrm \
	    fontconfig \
	    openssl \
	    nss \
	    libcap \
	    dbus-native \
	    dbus \
	    "

INSANE_SKIP = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"
INHIBIT_SYSROOT_STRIP = "1"

PACKAGES = "${PN}"

FILES_${PN} += " /usr/local/Qt-5.13.2 \
	       "

do_configure(){
	    	    
	    ./configure \
	    	    -opensource \
	    	    -confirm-license \ 
	    	    -release \
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

do_install(){
	    install -d ${D}/usr/local
	    make INSTALL_ROOT=${D} install
	    mv ${D}${STAGING_DIR_TARGET}/usr/local/Qt-5.13.2 ${D}/usr/local
	    find ${D} -type d -empty -delete
}

do_package_qa[noexec] = "1"
