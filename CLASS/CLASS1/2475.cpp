#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <queue>
#include <tuple>
#include <cstring>
using namespace std;
typedef long long ll;


int main(){
    cin.tie(0); cout.tie(0);
    ios::sync_with_stdio(false);

    int arr[5];
    for (int i=0;i<5;i++)
        cin>>arr[i];
    
    int result=0;
    for (int i=0;i<5;i++){
        result+=(arr[i]*arr[i]);
    }
    cout<<result%10;
}
