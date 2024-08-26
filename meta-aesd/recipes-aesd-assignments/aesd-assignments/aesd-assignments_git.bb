inherit update-rc.d

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-Dexatr;protocol=ssh;branch=master"

PV = "1.0+git${SRCPV}"

SRCREV = "ef4b7bffe73ef06b910b5da3da9c9fbd36dcb46b"

S = "${WORKDIR}/git/server"

FILES:${PN} += "${bindir}/aesdsocket"
TARGET_LDFLAGS += "-pthread -lrt"

export CROSS_COMPILE = "${TARGET_PREFIX}"
EXTRA_OEMAKE = "CC='${CC}' LDFLAGS='${TARGET_LDFLAGS}'"

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "S99aesdsocket"

do_configure () {
	:
}

do_compile () {
	oe_runmake
}

do_install () {
	install -d ${D}${bindir}
    install -m 0755 ${S}/aesdsocket ${D}${bindir}/

    install -d ${D}${sysconfdir}/init.d/
	install -m 0755 ${S}/S99aesdsocket ${D}${sysconfdir}/init.d/
}