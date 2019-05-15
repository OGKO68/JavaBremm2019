
#include <iostream>
using namespace std;

int main(){
    int turner = 0;
    cout << "cd classes" << endl << endl << endl;
    try{
        system("cd classes");
        system("javac *.java");
        system("cd ..");        
    }catch (...){
        cout << "an unexpected error occurred while parsing classes" << endl;
        return 1;
    }
    cout << "press any key to continue" << endl ;
    getchar();
    cout << "---------------------------" << endl;
    cout << "parsing files in kb folder" << endl << endl << endl;
    try{
        system("javac *.java");
    }catch (...){
        cout << "an unexpected error occurred while parsing kb" << endl;
        return 2;
    }
    return 0;
}
