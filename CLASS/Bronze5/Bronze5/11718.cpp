//
//  11718.cpp
//  Bronze5
//
//  Created by 최예헌 on 2022/07/11.
//

#include <iostream>
#include <vector>
using namespace std;

int main(int argc, const char * argv[]) {
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    string str;
    vector<string> v;
    
    while (!cin.eof()){
        getline(cin,str);
        v.push_back(str);
    }
    cin.ignore();
    for (int i=0;i<v.size();i++)
        cout<<v[i]<<"\n";
    return 0;
}
