//
//  2292.cpp
//  CLASS2
//
//  Created by 최예헌 on 2022/07/12.
//

#include <iostream>
using namespace std;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);
    
    long long n;
    cin>>n;
    
    long long temp = 1;
    long long k=1;
    if (n == 1){
        cout<<"1";
        return 0;
    }
    
    while (true){
        if (n >= temp+1 && n<=temp+(6*k))
            break;
        
        temp = temp+(6*k);
        k++;
        
    }
    cout<<k+1;
}

/*
 1
 2...7 : 2 , 1+6
 8...19 : 8, 7+12
 20...37 : 20, 19+18
 */
