//
//  15829.cpp
//  CLASS2
//
//  Created by 최예헌 on 2022/07/12.
//  pow 함수 사용 시 틀림

#include <iostream>
#include <cmath>
using namespace std;
#define MAX 1234567891

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n;
    string str;
    cin>>n>>str;
    unsigned long long result=0;
    
    for (int i=0;i<str.size();i++){
        unsigned long long temp = 1;
        for (int j=0;j<i;j++){
            temp*=31;
            temp%=MAX;
        }
        
        result+=((int)(str[i]-'a'+1)*temp);
        result%=MAX;
    }
    cout<<result%MAX;
}
