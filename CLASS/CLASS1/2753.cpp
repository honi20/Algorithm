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

    int n;
    cin>>n;
    
    if ((n%4==0 && n%100!=0)||n%400==0)
        cout<<"1";
    else
        cout<<"0";
}
