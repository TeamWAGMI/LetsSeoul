function ProfileInput({
  nicknameRef = null,
  name,
  maxLength,
  isEditable,
  value,
  handleInputChange,
  styles,
}) {
  return (
    <input
      ref={nicknameRef}
      disabled={!isEditable}
      name={name}
      type="text"
      maxLength={maxLength}
      className={`h-[34px] rounded-lg p-[10px] text-sm leading-none bg-white ${styles} ${
        isEditable ? "focus:ring focus:ring-wagmiLightGreen" : null
      }`}
      value={value}
      onChange={handleInputChange}
    />
  );
}

export default ProfileInput;
