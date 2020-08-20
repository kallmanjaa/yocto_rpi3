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
    build-essential \
    chrpath \
    socat \
    libsdl1.2-dev \
    xterm \
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


#RUN sysctl -w fs.inotify.max_user_watches=1048576 && sysctl -p

RUN useradd -ms /bin/bash kalmanjaa

#RUN curl https://storage.googleapis.com/git-repo-downloads/repo > /bin/repo

#RUN chmod a+x /bin/repo

RUN update-alternatives --install /usr/bin/python python /usr/bin/python2.7 1

RUN sed -i -e 's/# en_US.UTF-8 UTF-8/en_US.UTF-8 UTF-8/' /etc/locale.gen && \
    dpkg-reconfigure --frontend=noninteractive locales && \
    update-locale LANG=en_US.UTF-8

ENV LANG en_US.UTF-8 
