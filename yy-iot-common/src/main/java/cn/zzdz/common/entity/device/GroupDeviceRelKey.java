package cn.zzdz.common.entity.device;

public class GroupDeviceRelKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_device_rel.group_id
     *
     * @mbg.generated Mon Mar 16 15:49:13 CST 2020
     */
    private String groupId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_device_rel.device_id
     *
     * @mbg.generated Mon Mar 16 15:49:13 CST 2020
     */
    private String deviceId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_device_rel.group_id
     *
     * @return the value of group_device_rel.group_id
     *
     * @mbg.generated Mon Mar 16 15:49:13 CST 2020
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_device_rel.group_id
     *
     * @param groupId the value for group_device_rel.group_id
     *
     * @mbg.generated Mon Mar 16 15:49:13 CST 2020
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_device_rel.device_id
     *
     * @return the value of group_device_rel.device_id
     *
     * @mbg.generated Mon Mar 16 15:49:13 CST 2020
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_device_rel.device_id
     *
     * @param deviceId the value for group_device_rel.device_id
     *
     * @mbg.generated Mon Mar 16 15:49:13 CST 2020
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }
}