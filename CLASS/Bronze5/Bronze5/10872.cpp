//
//  10872.cpp
//  Bronze5
//
//  Created by 최예헌 on 2022/07/11.
//

#include <iostream>
using namespace std;

int main(int argc, const char * argv[]) {
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n;
    long long result=1;
    
    cin>>n;
    for (int i=0;i<n;i++)
        result*=(i+1);
    
    cout<<result;
    return 0;
}
