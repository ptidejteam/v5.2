%define  RELEASE 3
%define  rel     %{?CUSTOM_RELEASE} %{!?CUSTOM_RELEASE:%RELEASE}

%define lib_name log4cpp

Name: log4cpp
Version: 0.2.7
Release: %rel

Summary: Log for C++
Copyright: LGPL
Group: Development/Libraries
Vendor:  Bastiaan Bakker <bastiaan.bakker@lifeline.nl>
Packager: Cedric Le Goater <cedric@legoater.com>
Url: http://log4cpp.sourceforge.net/

Source: ftp://download.sourceforge.net/pub/sourceforge/log4cpp/%name-%version.tar.gz

Prefix: %_prefix
BuildRoot: %_tmppath/%name-%version-root

%description
Log for C++ is a library of classes for flexible logging to files, syslog,
and other destinations. It is modeled after the Log for Java library and
stays as close to its API as is reasonable.

%package devel
Summary: development tools for Log for C++
Group: Development/Libraries
Requires: %name = %version

%description devel
The %name-devel package contains the static libraries and header files
needed for development with %name.

%package doc
Summary: HTML formatted API documention for Log for C++
Group: Development/Libraries
Requires: %name = %version

%description doc
The %name-doc package contains HTML formatted API documention generated by
the popular doxygen documentation generation tool.

%prep
rm -rf $RPM_BUILD_ROOT

%setup
CFLAGS="$RPM_OPT_FLAGS" ./configure --prefix=%{prefix} --enable-doxygen 

%build
make

%install
rm -rf $RPM_BUILD_ROOT

make prefix=$RPM_BUILD_ROOT%{prefix} install

%clean
rm -rf $RPM_BUILD_ROOT

%post
/sbin/ldconfig

%postun
/sbin/ldconfig

%files
%defattr(-,root,root,755)
%attr(755,root,root) %prefix/lib/lib*.so.*
%doc AUTHORS COPYING INSTALL NEWS README THANKS ChangeLog

%files devel
%defattr(-,root,root,755)
%prefix/include/*
%prefix/man/*
%attr(755,root,root) %prefix/bin/log4cpp-config
%attr(755,root,root) %prefix/lib/lib*.so
%attr(644,root,root) %prefix/lib/*.*a

%files doc
%doc doc/html/*

