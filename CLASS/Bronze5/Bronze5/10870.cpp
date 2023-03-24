//
//  10870.cpp
//  Bronze5
//
//  Created by 최예헌 on 2022/07/11.
//

#include <iostream>
using namespace std;

int arr[22];

int solve(int n){
    if (n == 0)
        arr[n]=0;
    else if (n==1)
        arr[n]=1;
    else
        arr[n]=solve(n-1)+solve(n-2);
    
    return arr[n];
}

int main(int argc, const char * argv[]) {
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    int n;
    cin>>n;
    solve(n);
    cout<<arr[n];
    return 0;
}

