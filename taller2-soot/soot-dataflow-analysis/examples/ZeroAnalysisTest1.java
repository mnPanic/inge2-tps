public class ZeroAnalysisTest1 {
 public static int test1(int m, int n) {
    int x = 0;
    int k = x * n;
    int j = m / k;
    // IN(x)=IN(k)=Z,IN(m)=IN(n)=MZ,IN(j)=Undef
    return j;
}
}
