#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <queue>
#include <tuple>
#include <cstring>
#include <string>
#include <stack>
using namespace std;
typedef long long ll;
#define MAX 1000000001

int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int n,k;
    int result1=1,result2=1;
    cin>>n>>k;
    
    for (int i=0;i<k;i++){
        result1*=(n-i);
    }
    
    for (int i=0;i<k;i++){
        result2*=(k-i);
    }
    cout<<result1/result2;
}
