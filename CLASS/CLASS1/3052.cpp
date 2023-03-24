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
    int cnt[42]={0,};
    int result=0;
    
    for (int i=0;i<10;i++){
        cin>>x;
        cnt[x%42]++;
    }
    
    for (int i=0;i<42;i++){
        if (cnt[i]>0)
            result++;
    }
    cout<<result;
}
