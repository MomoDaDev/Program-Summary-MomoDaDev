// TurnOffPC.cpp: Definiert den Einstiegspunkt für die Konsolenanwendung.
//

#include "stdafx.h"
#include <fstream>
#include <vector>
#include <string>
#include <iostream>
#include <thread>
#include <windows.h>

using namespace std;

int main()
{
	
	cout << "Hi" << endl;
	SendMessage(HWND_BROADCAST, WM_SYSCOMMAND, SC_MONITORPOWER, (LPARAM)2);
	this_thread::sleep_for(5s);
	SendMessage(HWND_BROADCAST, WM_SYSCOMMAND, SC_MONITORPOWER, (LPARAM)-1);
	
	//system("shutdown -s");
	return 0;
}

