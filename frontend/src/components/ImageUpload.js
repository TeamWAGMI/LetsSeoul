function ImageUpload({ id }) {
  return (
    <div className="flex flex-col">
      <div className="w-24 h-24 bg-bgGray mb-2" />
      <div>
        <input
          className="hidden"
          id={id}
          type="file"
          name="file"
          accept="image/*"
          // onChange={(e) => handleChangeImage(e)}
        />
        <label
          className="block text-xs text-semibold text-center border rounded-lg p-2 cursor-pointer"
          htmlFor={id}
        >
          사진 등록하기
        </label>
      </div>
    </div>
  );
}

export default ImageUpload;
