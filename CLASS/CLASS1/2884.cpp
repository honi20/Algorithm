#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <queue>
#include <tuple>
#include <cstring>
#include <string>
using namespace std;
typedef long long ll;

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int a,b;
    int temp=45;
    
    cin>>a>>b;
    if (b>=temp)
        cout<<a<<" "<<b-temp;
    
    else{
        temp-=b;
        if (a==0)
            cout<<"23 "<<60-temp;
        else
            cout<<a-1<<" "<<60-temp;
    }
    
}
