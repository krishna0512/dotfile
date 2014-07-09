#!/bin/bash
# enable color support of ls and also add handy aliases
if [ -x /usr/bin/dircolors ]; then
    test -r ~/.dircolors && eval "$(dircolors -b ~/.dircolors)" || eval "$(dircolors -b)"
    alias ls='ls --color=auto'
    #alias dir='dir --color=auto'
    #alias vdir='vdir --color=auto'

    alias grep='grep --color=auto'
    alias fgrep='fgrep --color=auto'
    alias egrep='egrep --color=auto'
fi

# Add an "alert" alias for long running commands.  Use like so:
#   sleep 10; alert
alias alert='notify-send --urgency=low -i "$([ $? = 0 ] && echo terminal || echo error)" "$(history|tail -n1|sed -e '\''s/^\s*[0-9]\+\s*//;s/[;&|]\s*alert$//'\'')"'

# This defination of ll sorts the output according to the last modified file at the end.
alias ll='ls -Alrh --sort=time'
alias la='ls -A'
alias l='ls'
alias sl='ls'
alias ..='cd ..'
alias ...='cd ../..'
alias sleep='dbus-send --system --print-reply --dest=org.freedesktop.UPower /org/freedesktop/UPower org.freedesktop.UPower.Suspend'
alias suspend='sleep'
alias hibernate='pm-hibernate'
alias hiber='pm-hibernate'
alias vi='vim'
alias vim='vim -p'
alias v='view'
alias p='python'
alias bashrc='vim ~/.bashrc'
alias vimrc='vim ~/.vimrc'
alias bashalias='vim ~/.bash_aliases'
alias kill='kill -9'
alias more='less'
alias pop='popd'
alias push='pushd .'
# This calls the custom command @ /usr/bin/krishna-askpass for sudo authentication.
# This configuration is defined in sudo.conf file
alias sudo='sudo --askpass'
alias curl='curl -L'
# This g++ alias doesnt work inside the makefile
alias g++='g++ -Wall -g -std=c++0x'
# aliases for apt family
alias apt-install='sudo apt-get install'
alias apt-search='apt-cache search'
alias apt-update='sudo apt-get update; alert'
alias apt-upgrade='sudo apt-get upgrade; alert'
alias apt-remove='sudo apt-get remove --purge'
