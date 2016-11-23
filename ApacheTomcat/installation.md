# Installing apache

1. Download Apache binaries from: http://www.apachelounge.com/download/VC10/
	* httpd-2.4.23-win32.zip
	
2. Unzip apache to c:\apache24 (Keep directory structure c:\apache24\bin)
3. Unzip mod_jk to apache24/modules dir

# httpd.conf

1. Configure modules:
	* Add lines in modules section:
```
LoadModule proxy_module modules/mod_proxy.so
LoadModule proxy_ajp_module modules/mod_proxy_ajp.so
LoadModule proxy_balancer_module modules/mod_proxy_balancer.so
LoadModule proxy_connect_module modules/mod_proxy_connect.so
LoadModule proxy_express_module modules/mod_proxy_express.so
LoadModule proxy_fcgi_module modules/mod_proxy_fcgi.so
LoadModule proxy_ftp_module modules/mod_proxy_ftp.so
LoadModule proxy_html_module modules/mod_proxy_html.so
LoadModule proxy_http_module modules/mod_proxy_http.so
LoadModule proxy_http2_module modules/mod_proxy_http2.so
LoadModule proxy_scgi_module modules/mod_proxy_scgi.so
LoadModule proxy_wstunnel_module modules/mod_proxy_wstunnel.so

LoadModule slotmem_shm_module modules/mod_slotmem_shm.so

LoadModule xml2enc_module modules/mod_xml2enc.so
```

2. Find Listen property and change it to:
```
Listen 9998
```

3. Add proxy settings
```
ProxyPass / http://localhost:9997/
ProxyPassReverse / http://localhost:9997/
```

# Run spring-boot application

1. Clone https://github.com/Endeg/spring-petclinic
2. Run application with mvn spring-boot:run
