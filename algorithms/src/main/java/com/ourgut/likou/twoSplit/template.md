

## 二分法框架
int binarySearch(int[] nums, int target) {
    int left = 0, right = ...;//right取什么需要考虑length-1还是length
    while(...) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            ...
        } else if (nums[mid] < target) {
            left = ...
        } else if (nums[mid] > target) {
            right = ...
        }
    }
    return ...;
}

## 一个普通的二分查找

int binarySearch(int[] nums, int target) {
    int left = 0; 
    int right = nums.length - 1; // 注意
    while(left <= right) {
        int mid = left + (right - left) / 2;
        if(nums[mid] == target)
            return mid; 
        else if (nums[mid] < target)
            left = mid + 1; // 注意
        else if (nums[mid] > target)
            right = mid - 1; // 注意
    }
    return -1;
}

因为我们初始化 right = nums.length - 1
所以决定了我们的「搜索区间」是 [left, right]
所以决定了 while (left <= right)
同时也决定了 left = mid+1 和 right = mid-1

因为我们只需找到一个 target 的索引即可
所以当 nums[mid] == target 时可以立即返回

但是，这个算法存在局限性。比如说给你有序数组 nums = [1,2,2,2,3]，target 为 2，
此算法返回的索引是 2，没错。但是如果我想得到 target 的左侧边界，即索引 1，或者
我想得到 target 的右侧边界，即索引 3，这样的话此算法是无法处理的。
所以出现了左右边界的二分查找


## 寻找左侧边界的二分查找
int left_bound(int[] nums, int target) {
    if (nums.length == 0) return -1;
    int left = 0;
    int right = nums.length; // 注意
    while (left < right) { // 注意
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            right = mid;
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else if (nums[mid] > target) {
            right = mid; // 注意
        }
    }
    return left;
}


因为我们初始化 right = nums.length
所以决定了我们的「搜索区间」是 [left, right)
所以决定了 while (left < right)
同时也决定了 left = mid + 1 和 right = mid

因为我们需找到 target 的最左侧索引
所以当 nums[mid] == target 时不要立即返回
而要收紧右侧边界以锁定左侧边界

## 右侧边界的二分查找
int right_bound(int[] nums, int target) {
    if (nums.length == 0) return -1;
    int left = 0, right = nums.length;
    while (left < right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            left = mid + 1; // 注意
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else if (nums[mid] > target) {
            right = mid;
        }
    }
    return left - 1; // 注意
}
因为我们初始化 right = nums.length
所以决定了我们的「搜索区间」是 [left, right)
所以决定了 while (left < right)
同时也决定了 left = mid + 1 和 right = mid

因为我们需找到 target 的最右侧索引
所以当 nums[mid] == target 时不要立即返回
而要收紧左侧边界以锁定右侧边界

又因为收紧左侧边界时必须 left = mid + 1
所以最后无论返回 left 还是 right，必须减一


总结：
1.while(left <还是<= right)取决于你的left，right的开闭关系
[left,right],则是left<=right
[left,right),则是left<right
2.
中间:left=mid+1;left=mid-1;
左边界:
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            right = mid;
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else if (nums[mid] > target) {
            right = mid; // 注意
        }
右边界:
        if (nums[mid] == target) {
            left = mid + 1; // 注意
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else if (nums[mid] > target) {
            right = mid;
        }
(因为左右边界，right取的都是length，所以没有减一的操作，如果加了减一操作，那么最外面的一个元素访问不到)
(本质区别就是如果== target了，是left=mid还是right=mid，取决于你想取的边界
左边界：则==target时，right=mid，范围向左压缩
右边界：则==target时，left=mid+1，范围向右压缩，为什么是mid+1，因为left是闭区间[left,right))


## 但是其实我觉得这个套路也不一定适合，还是需要看情况，例如test2，就是end--
他的本质是因为不是单调递增了，所以只能不断减一去判断