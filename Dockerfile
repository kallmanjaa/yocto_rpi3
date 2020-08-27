#Download base image ubuntu 18.04
FROM ubuntu:18.04

ENV DEBIAN_FRONTEND noninteractive
ENV SYSTEMD_IGNORE_CHROOT yes
 
# Install dependencies
RUN dpkg --add-architecture amd64 \
	&& apt-get update \
	&& apt-get install -y \
	locales \
	python-minimal \
	make \
	gawk \
	wget \
	curl \
	git-core \
	diffstat \
	unzip \
	texinfo \
	gcc-multilib \
	g++-multilib \
	libc6-dev-i386 \
    	build-essential \
    	libfontconfig1-dev \
    	libnss3-dev \
    	ninja-build \
    	chrpath \
    	socat \
    	gperf \
    	bison \
    	flex \
    	pkg-config \
    	libpng-dev \
    	libsdl1.2-dev \
    	xterm \
    	libstdc++6 \
	openssh-client \
	rsync \
	cpio \
	libelf-dev \
	xz-utils \
	libncurses5-dev \
	libncursesw5-dev \
	device-tree-compiler \
	zlib1g-dev \
	libssl-dev \
	repo \
	bc \
	autoconf \
	subversion \
	#&& apt-get clean \
 	#&& rm -rf /var/lib/apt/lists/* \
    && echo "StrictHostKeyChecking no" >> /etc/ssh/ssh_config

RUN mkdir -p /usr/include/nss3 && ln -sf /usr/include/nss/* /usr/include/nss3
RUN ln -sf /usr/include/nspr/* /usr/include
RUN ln -sf /usr/lib/x86_64-linux-gnu/nss/* /usr/lib

#RUN sysctl -w fs.inotify.max_user_watches=1048576 && sysctl -p

RUN useradd -ms /bin/bash kalmanjaa

RUN update-alternatives --install /usr/bin/python python /usr/bin/python2.7 1

RUN sed -i -e 's/# en_US.UTF-8 UTF-8/en_US.UTF-8 UTF-8/' /etc/locale.gen && \
    dpkg-reconfigure --frontend=noninteractive locales && \
    update-locale LANG=en_US.UTF-8

ENV LANG en_US.UTF-8 
