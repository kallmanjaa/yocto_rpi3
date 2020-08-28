SUMMARY = "qt navigation"
DESCRIPTION = "qt navigation"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/kallmanjaa/qt-navigation.git;branch=feature/qt_navigation;protocol=http"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

SRC_URI[md5sum] = "c3f4344ff05151f34adb39f008c3deca"
SRC_URI[sha256sum] = "2c8a45ff7962cc252edde3c7bcab29085003a87b152bb409cc1b504be26ff0e0"

SYSROOT_DIRS += "/usr/local"

SRCREV = "${AUTOREV}"

EXTRA_OECMAKE += "-DPREFIX_PATH=${STAGING_DIR_TARGET}/usr/local/Qt-5.13.2" 

DEPENDS += "qt5-base \
            qt5-declarative \
            qt5-quickcontrols \
            qt5-location \
            qt5-svg \
            qt5-virtualkeyboard \
            qt5-webengine \
            "

inherit pkgconfig cmake

PACKAGES = "${PN}"

FILES_${PN} += " /usr/local/bin \
	       "
INSANE_SKIP = "1"
INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"
INHIBIT_SYSROOT_STRIP = "1"

SRC_URI += " file://navigation.service"
DISTRO_FEATURES_append = "systemd"

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE_${PN} = "navigation.service"


inherit systemd

do_install_append() {

             install -d ${D}${systemd_unitdir}/system/
             install -m 0644 ${WORKDIR}/navigation.service ${D}${systemd_unitdir}/system/
}

#Pack the path
FILES_${PN} += " ${systemd_unitdir}/system/navigation.service"

REQUIRED_DISTRO_FEATURES= "systemd"

