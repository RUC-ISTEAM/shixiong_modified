#include <head.h>
#include <sys/mman.h>
#include <signal.h>
#include <unistd.h>
#include <malloc.h>

FILE *fp;

// ELF 文件头关于字节区的相关信息
int e_shoff = 0;
int e_shentsize = 0;
int e_shnum = 0;
int e_shstrndx = 0;

int e_entry = 0;
int E_ENTRY_OFFSET = 24;
int enSize = 0;
//--------------------------
int E_SHOFF_OFFSET = 32;    // 字节区的起始偏移量
int E_SHENTSIZE_OFFSET = 46;    // 每个字节区大小的偏移量
int E_SHNUM_OFFSET = 48;    // 字节区数目的偏移量
int E_SHSTRNDX_OFFSET = 50;  // 节区名称所用字符串表的节区编号
//-------------------------------------------------------------------------------
// rel.plt 相关信息
int relplt_now = 0;
int relplt_name = 0;
int relplt_offset = 0;
int relplt_size = 0;
int relplt_shentsize = 0;
int relplt_num = 0;
int relplt_link = 0;
// 偏移位置
int RELPLT_OFFSET_OFFSET = 16;
int RELPLT_SIZE_OFFSET = 20;
int RELPLT_LINK_OFFSET = 24;
int RELPLT_SHENTSIZE_OFFSET = 36;
//------------------------------------------------------------------------------------
int sym_link = 0;
int sym_shentsize = 0;
int SYM_LINK_OFFSET = 24;
int SYM_SHENTSIZE_OFFSET = 32;

int functionLength = 0; // 函数名称的长度
int fileLength = 0; // 所用 so 库的数目
int charLength = sizeof(char);
int tableLength = 0;
int GlobalFlags = false;
char fileName[50][128] = {0}; // 所用的so库
char functionName[128] = {0};
char value2[2] = {0}, value4[4] = {0}; // 用于承载取出的内容

int gotOffset[50] = {0};    // 记录每一个 so 库的 GOT 入口表项的偏移地址
int baseOffset[50] = {0};   // 记录每一个 so 库的基地址
int both[50] = {0};         // 判断是否一个函数的基地址和 GOT 入口表项均被找到
int *GOT;
int pageSize = 0;


// 初始化 connect（）函数所关联的 so 库
void initFileConnect(){
    LOG("------ Function : initFileConnect() - init the connect function");
    strcpy(fileName[0],"audio.so");
	strcpy(fileName[1],"libawDTCP.so");
	strcpy(fileName[2],"libbluetooth.so");
	strcpy(fileName[3],"libBMapApiEngine_v1_3_3.so");
	strcpy(fileName[4],"libBMapApiEngine_v1_3_4.so");
	strcpy(fileName[5],"libbtio.so");
	strcpy(fileName[6],"libchromium_net.so");
	strcpy(fileName[7],"libcrypto.so");
    strcpy(fileName[8],"libcutils.so");
    strcpy(fileName[9],"libdbus.so");
	strcpy(fileName[10],"libdss.so");
	strcpy(fileName[11],"libdvm.so");
	strcpy(fileName[12],"libDxDrmCAPI.so");
	strcpy(fileName[13],"libDxDrmJava.so");
	strcpy(fileName[14],"libffmpeg_qiyi_neon.so");

	strcpy(fileName[15],"libffmpeg_qiyi_vfp.so");
	strcpy(fileName[16],"libflashplayer.so");
	strcpy(fileName[17],"libhhtrade.so");
	strcpy(fileName[18],"libjavacore.so");
	strcpy(fileName[19],"libjDTCPServer.so");
	strcpy(fileName[20],"libjUPnPCP.so");
	strcpy(fileName[21],"libLaputaEngine.so");
	strcpy(fileName[22],"libmdnssd.so");
	strcpy(fileName[23],"libmediaplayerservice.so");
	strcpy(fileName[24],"libmmcamera_interface2.so");
	strcpy(fileName[25],"libmsc.so");
	strcpy(fileName[26],"liborion.so");
	strcpy(fileName[27],"libqmi_client_qmux.so");
	strcpy(fileName[28],"libqmi.so");
	strcpy(fileName[29],"libtunein.ffmpeg.so");

	strcpy(fileName[30],"libtunein.mms.so");
	strcpy(fileName[31],"libv8.so");
	strcpy(fileName[32],"libVideoCtrl.so");
	strcpy(fileName[33],"libvlccore.so");
	strcpy(fileName[34],"libvoipCodec.so");
	strcpy(fileName[35],"libvoipCodec_v5.so");
	strcpy(fileName[36],"libvoipCodec_v7a.so");
	strcpy(fileName[37],"libvoipMain.so");
	strcpy(fileName[38],"libwpa_client.so");
	strcpy(fileName[39],"libwsp.so");

	functionLength = strlen("connect") + 1;
	fileLength = 40;
	tableLength = 9;
	strcpy(functionName,"connect");
}
// 初始化 getaddrinfo（）函数所关联的 so 库
void initFileGetAddrInfo(){
    LOG("------ Function : initFileGetAddrInfo() - init the getaddrinfo function");
    strcpy(fileName[0],"libchromium_net.so");
	strcpy(fileName[1],"libnativehelper.so");
	strcpy(fileName[2],"libnetutils.so");

	functionLength = strlen("getaddrinfo") + 1;
	fileLength = 3;
	strcpy(functionName,"getaddrinfo");
}
// 初始化 ioctl（）函数所关联的 so 库
void initFileIoctl(){
    LOG("------ Function : initFileIoctl() - init the ioctl function");
    strcpy(fileName[0],"libandroid_runtime.so");
	strcpy(fileName[1],"libbcinfo.so");
	strcpy(fileName[2],"libcrypto.so");
	strcpy(fileName[3],"libchromium_net.so");
	strcpy(fileName[4],"libcutils.so");
	strcpy(fileName[5],"libbinder.so");
	strcpy(fileName[6],"libhardware_legacy.so");
	strcpy(fileName[7],"libmtp.so");
	strcpy(fileName[8],"libnativehelper.so");
	strcpy(fileName[9],"libnetutils.so");
	strcpy(fileName[10],"libusbhost.so");
	strcpy(fileName[11],"libutils.so");
	strcpy(fileName[12],"libandroid_servers.so");
	strcpy(fileName[13],"libdiskconfig.so");
	strcpy(fileName[14],"libext4_utils.so");
	strcpy(fileName[15],"libinput.so");
	strcpy(fileName[16],"libreference-ril.so");

	functionLength = strlen("ioctl") + 1;
	fileLength = 17;
	tableLength = 9;
	strcpy(functionName,"ioctl");
}
// 初始化 Binder 函数所关联的 so 库
void initBinder(){
	LOG("------ Function : initBinder ------");
    strcpy(fileName[0],"libandroid_runtime.so");
	functionLength = strlen("_ZN7android12ProcessState16getContextObjectERKNS_2spINS_7IBinderEEE")+1;
	fileLength = 1;
	tableLength = 9;
	strcpy(functionName,"_ZN7android12ProcessState16getContextObjectERKNS_2spINS_7IBinderEEE");
}

// 字符串转化为整型数据
int charToInt(char *chars,int num)
{
        int i;
        int ans = 0;

        for (i = num - 1; i >= 0; i --)
            ans = (ans << 8) + (256 + chars[i]) % 256;

        return ans;
}
// 根据 ELF 头信息的得到节区的一般信息
void GetElfSecssionInfo(){
    LOG("------ Function : GetElfSecssionInfo() ------");
    // 节区的偏移量
    fseek(fp,E_SHOFF_OFFSET,SEEK_SET);
    fread(value4,charLength,four,fp);
    e_shoff = charToInt(value4,four);
    // 每一个节区的大小
    fseek(fp,E_SHENTSIZE_OFFSET - E_SHOFF_OFFSET - four,SEEK_CUR);
    fread(value2,charLength,two,fp);
    e_shentsize = charToInt(value2,two);
    // 节区的数目
    //fseek(fp,E_SHNUM_OFFSET,SEEK_SET);
    fread(value2,charLength,two,fp);
    e_shnum = charToInt(value2,two);
    // 节区名称所用字符串表所在的节区编号
    //fseek(fp,E_SHSTRNDX_OFFSET,SEEK_SET);
    fread(value2,charLength,two,fp);
    e_shstrndx = charToInt(value2,two);

    /*fseek(fp,E_ENTRY_OFFSET,SEEK_SET);
    fread(value4,charLength,four,fp);
    e_entry = charToInt(value4,four);
    enSize = e_entry & 0xffff;
    */return;
}
// 将文件指针移到节区
void jmpToSecssion(int num){
    fseek(fp,e_shoff,SEEK_SET);
    fseek(fp,e_shentsize * num,SEEK_CUR);
}
// 将文件指针移到字符串表
void jmpToCharTable(int num){
    jmpToSecssion(num);

    fseek(fp,RELPLT_OFFSET_OFFSET,SEEK_CUR);
    fread(value4,charLength,four,fp);
    relplt_offset = charToInt(value4,four);

    fseek(fp,relplt_offset,SEEK_SET);
}
// 判断第 num 个节区是否是 .rel.plt 所在节区
int isRelPltSecssion(int num){
    char name[20] = {0};

    jmpToSecssion(num);
    fread(value4,charLength,four,fp);
    relplt_name = charToInt(value4,four);

    jmpToCharTable(e_shstrndx);
    fseek(fp,relplt_name,SEEK_CUR);
    fread(name,charLength,tableLength,fp);

   // LOG("------- The secssion name is : %s ------",name);
    //printf("The secssion name is : %s \n",name);
    if(strcmp(name,".rel.plt") == 0){
        LOG("------ The number is : %d ------",relplt_now);
        LOG("------ The .rel.plt address is : %p",(void *)(e_shoff + relplt_now * e_shentsize));
        //printf("The number is : %d\n",relplt_now);
        return true;
    }
    return false;
}
// 判断是否能够找到 .rel.plt 表所在节区
int GetRelPltSecssion(){
    for(relplt_now = 0; relplt_now < e_shnum; relplt_now ++){
        if(isRelPltSecssion(relplt_now))
            return true;
    }
    LOG("------ The RelPlt Table is not finded! ------");
    //printf("The RelPlt Table is not finded! \n");
    return false;
}
// 根据 .elf.plt 所在节区获取 .rel.plt 的基本信息
void GetRelPltSecssionInfo(){
    LOG("------ Function : GetRelPltSecssionInfo() ------");
    // 跳转到 relplt 所在节区
    jmpToSecssion(relplt_now);
    // relplt 表的大小
    fseek(fp,RELPLT_SIZE_OFFSET,SEEK_CUR);
    fread(value4,charLength,four,fp);
    relplt_size = charToInt(value4,four);
    // 符号表所在节区的索引
    fread(value4,charLength,four,fp);
    relplt_link = charToInt(value4,four);
    // 每一个 relplt 项的大小
    fseek(fp,RELPLT_SHENTSIZE_OFFSET - RELPLT_LINK_OFFSET - four,SEEK_CUR);
    fread(value4,charLength,four,fp);
    relplt_shentsize = charToInt(value4,four);

    if(relplt_shentsize != 0)
        relplt_num = relplt_size / relplt_shentsize;
}
//
int isRelPltFunction(int num){

    int relplt_info = 0;
    int relplt_sym  = 0;
    int sym_name = 0;
    char name[128] = {0};
    jmpToCharTable(relplt_now);
    //
    fseek(fp,num * relplt_shentsize + four,SEEK_CUR);
    fread(value4,charLength,four,fp);
    relplt_info = charToInt(value4,four);
    //
    relplt_sym = relplt_info >> 8;
    //printf("relplt_sym = %d\n",relplt_sym);

    jmpToSecssion(relplt_link);
    fseek(fp,SYM_LINK_OFFSET,SEEK_CUR);
    fread(value4,charLength,four,fp);
    sym_link = charToInt(value4,four);
    //printf("sym_link = %d\n",sym_link);

    fseek(fp,(SYM_SHENTSIZE_OFFSET - SYM_LINK_OFFSET),SEEK_CUR);
    fread(value4,charLength,four,fp);
    sym_shentsize = charToInt(value4,four);
    //printf("sym_shentsize = %d\n",sym_shentsize);


    jmpToCharTable(relplt_link);
    fseek(fp,relplt_sym * sym_shentsize,SEEK_CUR);
    fread(value4,charLength,four,fp);
    sym_name = charToInt(value4,four);
    //printf("sym_name = %d\n",sym_name);

    jmpToCharTable(sym_link);
    fseek(fp,sym_name,SEEK_CUR);
    fread(name,charLength,functionLength,fp);


    //printf("The Function name is : %s\n",name);
    //LOG("------ The Function name is : %s ------",name);
    if(strcmp(name,functionName) == 0){
    	LOG("Hello World ----|||||||||||||||||||||||||||||||||||||||||||||");
        return true;
    }
    return false;
}
// 判断是否能够找到函数所在项
int GetRelPltFunction(){
    int i = 0;
    int flags = false;
    for(i = 0; i < relplt_num ; i ++){
        if(isRelPltFunction(i))
        {
            flags = true;
            break;
        }
    }

    if(i == relplt_num){
//        printf("The function name is not finded!\n");
        LOG("------ The function name is not finded! ------");
        flags = false;
    }
    if(flags == true){
        jmpToCharTable(relplt_now);
        fseek(fp,i*relplt_shentsize,SEEK_CUR);
        fread(value4,charLength,four,fp);
        return charToInt(value4,four);
    }else{
        return false;
    }
}
// 得到 Got 入口项的偏移地址
int getGotOffset(char *filename){
    fp = fopen(filename,"rb");

    if(fp == NULL){
        //printf("the --- %p --- is opened failed !\n",fileName);
        LOG("------ the --- %p --- is opened failed ! ------",fileName);
        GlobalFlags = false;
        return false;
    }
    // 根据 Elf 获取节区的基本信息并找到 .rel.plt 所在节区
    GetElfSecssionInfo();
    if(!GetRelPltSecssion()){
        GlobalFlags = false;
        return false;
    }
    // 根据 .elf.plt 所在节区获取 .rel.plt 的基本信息
    GetRelPltSecssionInfo();
    int v = GetRelPltFunction();

    if(!v){
        GlobalFlags = false;
        return false;
    }
    return v;
}
// 寻找 so 库的基地址
void findSoAddress(){
    int i = 0, j = 0;
    int pid = 0, Number = 0;
    char mapName[64] = {0};
    char tmp[256] = {0};
    FILE *map;

    pid = getpid();


    //printf("pid = %d\n",pid);
    LOG("------ pid = %d ------",pid);
	sprintf(mapName,"/proc/%d/maps",pid);
	//printf("mapName = %s\n",mapName);
	LOG("------ mapName = %s ------",mapName);

	map = fopen(mapName,"r");

	if(map == NULL)
	{
        //printf("The Map File Is Failed!\n");
        LOG("------ The Map File Is Failed! ------");
        return;
	}

	while (true)
	{
		memset(tmp,0,sizeof(tmp));
		if (fgets(tmp,256,map) == NULL)
		{
            //printf("The tmp is NULL\n");
            LOG("------ The tmp is NULL ------");
            break;
		}
		//LOG("------ %s ------",tmp);
		for (i = 0; i < fileLength; i ++)
		{
			if (strstr(tmp,fileName[i]))
			{
				LOG("------ %s ------",tmp);
				if (baseOffset[i] == 0)
				{
					both[i]++;
					for (j = 0; j < 8; j ++)
					{
						Number = 0;
						if ('0' <= tmp[j] && tmp[j]<='9')
							Number = tmp[j]-'0';
						else
							Number = tmp[j]-'a'+ 10;
						baseOffset[i] = baseOffset[i]*16 + Number;
					}
				}
			}
        }
	}
	return ;
}
// 初始化
void init(int type){
    if(type == 0){
        initFileConnect();
    }else if(type == 1){
        initFileGetAddrInfo();
    }else if(type == 2){
        initFileIoctl();
    }else if(type == 3){
    	initBinder();
    }
}
// 遍历所有的 so 库文件，将每一个与目标函数相关的 so 库里面的地址都记录下来
void findSoFunctionAddress(){
    int i = 0;
    char fileNameSo[128] = {0};
    for(i = 0; i < fileLength; i ++)
    {
        memset(fileNameSo,0,sizeof(fileNameSo));
        strcpy(fileNameSo,"/system/lib/");
        strcat(fileNameSo,fileName[i]);
        GlobalFlags = true;
        gotOffset[i] = getGotOffset(fileNameSo);
        if(GlobalFlags)
        {
            both[i] ++;
        }else
        {
            //printf("The %s is not got!\n",fileNameSo);
            LOG("------ The %s is not got! ------",fileNameSo);
        }
    }
}
// 修改函数地址
void modifyFunctionAddress(int newAddr){
    int i = 0;


    for (i = 0; i < fileLength; i ++)
	{
		if (both[i] == 2)
		{
			GOT = (int *)(gotOffset[i] + baseOffset[i]);
			LOG("------ the base address is : %p",(void *)(baseOffset[i]));
			LOG("------ the address is : %p",(void *)(gotOffset[i] + baseOffset[i]));
			LOG("------ the pageSize is : %d",pageSize);
			//LOG("------ the new address is : %p\n",(void *)((baseOffset[i] + e_shoff)/pageSize * pageSize));
            if(mprotect((void *)( ( (baseOffset[i] + gotOffset[i]) / pageSize ) * pageSize),pageSize,PROT_READ | PROT_WRITE) != 0){
            	LOG("------ mprotect is error------");
            }
			(*GOT) = (int) newAddr;
		}
    }
}
// 修改 GOT 表
void changeGot(int newAddr,int type){
	pageSize = sysconf(_SC_PAGE_SIZE);
	if(pageSize == -1){
		LOG("------ pageSize ------");
	}

    init(type);
    //gotOffset[0] = getGotOffset("/lib/i386-linux-gnu/libc.so.6");
    //fileLength = 1;

    findSoFunctionAddress();
    LOG("---------------------------------------------------------------------");
    findSoAddress();
    LOG("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    //printf("++++++++++++++++++++++\n");
    LOG("=====================================================================");
    modifyFunctionAddress(newAddr);
}
/*
int main(int argc,char *argv[]){
    changeGot(1,0);
}*/


