" Setups section.
set nocp

" First of all setting up Vundle
filetype off
set rtp+=~/.vim/bundle/vundle
call vundle#rc()

" Vundle package.
" Its nessacary to let vundle manage itself.
Plugin	'gmarik/vundle'

" Now its time for other plugins.
Plugin	'scrooloose/nerdtree.git'
Plugin	'docunext/closetag.vim.git'
Plugin 	'vim-scripts/supertab.git'
Plugin 	'vim-scripts/c.vim'
Plugin	'scrooloose/syntastic'
Plugin 	'jelera/vim-javascript-syntax'
Plugin 	'pangloss/vim-javascript'
" This plugin requires an updated version of vim
" Plugin 	'Valloric/YouCompleteMe'

filetype plugin indent on
" Vundle setup finished.

set smartindent
set cindent
set ruler
set nu
set nohlsearch
set showcmd
set incsearch
set smartcase
set cul
set tabstop=4
set shiftwidth=4
set mouse=a

" Automatically cd into the directory that file is in
set autochdir

" tab completion stuff
set wildmenu
set wildmode=list:longest,full

" use English for spell checking but don't spell check by default
if version >= 700
	set spl=en spell
	set nospell
endif

" Abbreviations section.
cnoreabbrev	<expr>	h getcmdtype() == ":" && getcmdline() == 'h' ? 'tab help' : 'h'
cnoreabbrev <expr>  W getcmdtype() == ":" && getcmdline() == 'W' ? 'w' : 'W'
cnoreabbrev <expr>  Q getcmdtype() == ":" && getcmdline() == 'Q' ? 'q' : 'Q'
cnoreabbrev <expr>  qq getcmdtype() == ":" && getcmdline() == "qq" ? "q!" : "qq"
cnoreabbrev <expr>  Wq getcmdtype() == ":" && getcmdline() == "qq" ? "wq" : "qq"
inoreabbrev @@		kt.krishna.tulsyan@gmail.com

" Mappings section.
cnoremap	w!!		w !sudo tee > /dev/null %
cnoremap	man 	Man
inoremap	jk		<esc>
inoremap	kj		<esc>
nnoremap	<C-h>	:set hls!<CR>
nnoremap	H		<C-w>h
nnoremap	J		<C-w>j
nnoremap	K		<C-w>k
nnoremap	L		<C-w>l
nnoremap	Q		:q<CR>

" Toggles the NERDTree
nnoremap	<F10>	:NERDTreeToggle<CR>

" Auto commands
autocmd InsertEnter,InsertLeave		*		set cul!

" Plugins section.
syntax enable
runtime ftplugin/man.vim
runtime macros/matchit.vim

let mapleader=";"
