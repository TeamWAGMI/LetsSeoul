function ProfileInput({
  nicknameRef = null,
  name,
  maxLength,
  placeholder,
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
      placeholder={placeholder}
      className={`h-[34px] rounded-lg p-[10px] leading-none bg-white ${styles} ${
        isEditable ? "focus:ring focus:ring-wagmiLightGreen" : null
      }`}
      value={value}
      onChange={handleInputChange}
    />
  );
}

export default ProfileInput;
