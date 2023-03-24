//
//  10757.cpp
//  Bronze5
//
//  Created by 최예헌 on 2022/07/11.
//

#include <iostream>
#include <string>
using namespace std;

int main(int argc, const char * argv[]) {
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    string a,b;
    int n=0,c=0;
    string result="";
    
    cin>>a>>b;
    int aidx = a.size()-1;
    int bidx = b.size()-1;
    
    while (aidx>=0 && bidx>=0){
        int temp = (int)(a[aidx]-'0')+(int)(b[bidx]-'0')+c;
        n = temp%10;
        c = temp/10;
        result = to_string(n)+result;
        aidx--;
        bidx--;
    }
    
    if (aidx >= 0){
        while (aidx>=0){
            int temp = (int)(a[aidx]-'0')+c;
            n = temp%10;
            c = temp/10;
            result = to_string(n)+result;
            aidx--;
        }
    }
    
    else if (bidx >= 0){
        while (bidx>=0){
            int temp = (int)(b[bidx]-'0')+c;
            n = temp%10;
            c = temp/10;
            result = to_string(n)+result;
            bidx--;
        }
    }
    if (c == 0)
        cout<<result;
    else
        cout<<to_string(c)+result;
    return 0;
}

