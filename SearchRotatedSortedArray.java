class SearchRotatedSortedArray {
    public int search(int[] nums, int target) {
        int index = checkIfArrayRotated(nums);
        if (index > -1) {
            nums = rotateArrayAtIndex(index, nums);
        }
        int found = binarySearch(nums, target);
        if (found > -1 && index > -1) {
            found = found + index + 1;
            if (found >= nums.length) {
                found %= nums.length;
            }
        }
        return found;
    }
    
    private int checkIfArrayRotated(int[] nums) {
        int i = 0, j = nums.length - 1;
        int index = -1;
        while (j > i) {
            if (nums[i] > nums[j]) {
                j--;
                index = j;
            }
            else {
                break;
            }
        }
        return index;
    }

    private int[] rotateArrayAtIndex(int index, int[] arr) {
        int[] rotatedArr = new int[arr.length];
        System.arraycopy(arr, index+1, rotatedArr, 0, (arr.length - 1 - index));
        System.arraycopy(arr, 0, rotatedArr, (arr.length - 1 - index), index+1);
        return rotatedArr;
    }
    
    private int binarySearch(int[] nums, int target) {
        int found = -1;
        int start = 0;
        int end = nums.length - 1;
        int mid = getMidIndex(start, end);
        if ((start + end) % 2 != 0) {
            mid = ((start + end) + 1) / 2;
        }
        else {
            mid = nums.length / 2;
        }
        while (start <= end) {
            if (nums[mid] > target) {
                end = mid - 1;
            }
            else if (nums[mid] < target) {
                start = mid + 1;
            }
            else {
                found = mid;
                break;
            }
            mid = getMidIndex(start, end);
        }
        return found;
    }
    
    private int getMidIndex(int start, int end) {
        if ((start + end) % 2 != 0) {
            return ((start + end) + 1) / 2;
        }
        else {
            return (start + end) / 2;
        }
    }
}