#include <head.h>

#include <linux/in6.h>
#include <linux/in.h>
#include <linux/socket.h>
#include <linux/binder.h>
//#include <binder/BpBinder.h>
//#include <utils/IPCThreadState.h>
//#include <utils/ProcessState.h>
//#include <cutils/process_name.h>
//#include <utils/String8.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/ioctl.h>

char debugString[100];

typedef int socklen_t;

int (*connect_real)(int, const struct sockaddr *, socklen_t);

int myconnect(int sockfd, const struct sockaddr *serv_addr, socklen_t  addrlen)
{
	//printLog("ccy: myconnect","ccy: my connect starts");
/*
	struct sockaddr_in6 * ipv6_socket = (struct sockaddr_in6 *) serv_addr;
	struct in6_addr ipv6_addr = ipv6_socket->sin6_addr;

	struct sockaddr_in  * ipv4_socket = (struct sockaddr_in  *) serv_addr;
	struct in_addr  ipv4_addr = ipv4_socket->sin_addr;

	char web_addr[70];
	int web_addrlen=0;

	sa_family_t sin6_family = ipv6_socket->sin6_family;
	sa_family_t sin4_family = ipv4_socket->sin_family;

	if (sin6_family == AF_INET6)
	{
		*
		 * 每8位二进制数对应成一个0～255的十进制数
		 * ipv6共128位，所以，共有16个十进制数
		 * 每个十进制数之间用冒号':'分开
		 *
		 * ipv4的地址用ipv6表示地址时(ipv6兼容ipv4)：
		 * 有如下格式：::ffff:192.168.89.9
		 * 即：255:255:192.168.89.9
		 *

		int i,j;
		char tmp[4]={0};

		for (i=0;i<16;i++)
		{
			int2char(ipv6_addr.in6_u.u6_addr8[i],tmp);
			for (j=0;tmp[j]!=0;j++)
				web_addr[web_addrlen++]=tmp[j];
			web_addr[web_addrlen++]=':';
		}
		web_addrlen--;
		web_addr[web_addrlen]='\0';

		//Debug
		char tmpString[70];
		sprintf(tmpString,"ccy: %s",web_addr);
		//printLog("ccy: myconnect",tmpString);
	}
	else if (sin4_family == AF_INET)
	{
		//printLog("ccy: myconnect","ccy: ipv4 protocol : OK");
	}
	else
	{
		//printLog("ccy: myconnect","ccy: other protocol : OK");
	}

	connect_printDialog(web_addr);


	//printLog("ccy: myConnect","ccy: printDialog");

	int l;
	int state = -1;
	for (l=0;l>=0;l++)
	{
		if (l==500)
		{
			l=0;
			state = connect_waitUser();
			if (state != -1)
			{
				memset(debugString,0,sizeof(debugString));
				sprintf(debugString,"ccy: l = %d", state);
				//printLog("ccy: myconnect",debugString);
				break;
			}
		}
	}

	if (state == 0) return 0;
	else return	connect_real(sockfd, serv_addr, addrlen);*/
	LOG("------ The New Connect Function ------");
	return connect_real(sockfd,serv_addr,addrlen);
}

void modConnect()
{
	int *GOT;
	void *handle;

	handle = dlopen("libc.so",RTLD_LAZY);

	//printLog("ccy: modConnect","ccy: start modConnect in Native C: OK");
	LOG("------ Start modConnect in Native C ------");

	if (handle == NULL)
	{
		//printLog("ccy: modConnect","ccy: handle is null");
		LOG("------ modConnect : handle is null ------");
	}
	else
	{
		//printLog("ccy: modConnect","ccy: GOT table starts changing: OK");
		LOG("------ modConnect : GOT Table starts Exploting ------");

		connect_real = dlsym(handle,"connect");

		changeGot((int)myconnect,0);

        LOG("------ modConnect : GOT Table has been changed ------");
		//printLog("ccy: modConnect","ccy: GOT table has been changed: OK");
	}
	return;
}






