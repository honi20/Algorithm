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

    int A,B;
    cin>>A>>B;
    if (A>B)
        cout<<">";
    else if (A==B)
        cout<<"==";
    else
        cout<<"<";
}
