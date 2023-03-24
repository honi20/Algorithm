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

    int x;
    int result_idx;
    int result=0;
    
    for (int i=0;i<9;i++){
        cin>>x;
        if (result<x){
            result=x;
            result_idx=i+1;
        }
    }
    cout<<result<<"\n"<<result_idx;
}
