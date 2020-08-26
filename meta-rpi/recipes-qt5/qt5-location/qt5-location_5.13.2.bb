SECTION = "QT5"

DESCRIPTION = "QT5-location for RPI3 devices"

LICENSE = "GPL-2.0+"

LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE.GPL2;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/qt/qtlocation.git;branch=5.13.2;protocol=http"

SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

SYSROOT_DIRS += "/usr/local"

EXTRA_OEMAKE = " \
    		MAKEFLAGS='${PARALLEL_MAKE}' \
    	       "

DEPENDS += "qt5-base qt5-declarative qt5-quickcontrols"

do_configure(){

	cat > "${STAGING_DIR_TARGET}/usr/local/Qt-5.13.2/bin/qt.conf" <<EOF
[Paths]
[EffectivePaths]
Prefix=..
[DevicePaths]
Prefix=/usr/local/Qt-5.13.2
[Paths]
Prefix=/usr/local/Qt-5.13.2
HostPrefix=${STAGING_DIR_TARGET}/usr/local/Qt-5.13.2
Sysroot=${STAGING_DIR_TARGET}
SysrootifyPrefix=true
TargetSpec=devices/linux-rasp-pi3-g++
HostSpec=linux-g++
EOF
	${STAGING_DIR_TARGET}/usr/local/Qt-5.13.2/bin/qmake -makefile
}

INSANE_SKIP = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"
INHIBIT_SYSROOT_STRIP = "1"

do_install(){

	install -d ${D}/usr/local
	make INSTALL_ROOT=${D} install
	find ${D} -type d -name "Qt-5.13.2" -exec mv -t ${D}/usr/local {} +
	find ${D} -type d -empty -delete

}

PACKAGES = "${PN}"

FILES_${PN} += " /usr/local/Qt-5.13.2 \
	       "

do_package_qa[noexec] = "1"
