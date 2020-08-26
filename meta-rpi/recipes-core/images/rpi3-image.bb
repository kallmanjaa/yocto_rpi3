# base the image on minimal
include recipes-core/images/core-image-minimal.bb

IMAGE_INSTALL_append += "\
		kernel-rpi \
		openssh \
		openssl \
		ninja \
		zlib \
		gdb \
		dbus \
		fontconfig \
		net-tools \
		libdrm-dev \
		libxdamage-dev \
		wayland \
		libxext \
		egl \
		gperf \
		alsa-lib \
		dbus \
		nss \
		qt5-base \
		qt5-declarative \
		qt5-quickcontrols \ 
    		qt5-svg \
    		qt5-virtualkeyboard \
    		qt5-location \
    		qt5-webengine \
		"

DEPENDS += "boot"

USE_DEPMOD = "0"

IMAGE_BOOT_FILES = "\
					boot_partition/kernel.img \
					boot_partition/overlays/*;overlays/ \
					boot_partition/bcm2710-rpi-3-b.dtb \
					"
