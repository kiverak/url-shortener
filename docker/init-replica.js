const config = {
    _id: "rs0",
    members: [
        { _id: 0, host: "localhost:27017" }
    ]
};

try {
    rs.initiate(config);
    print("✅ Реплика-сет rs0 успешно инициализирован");
} catch (e) {
    print("⚠️ Инициализация уже выполнена или ошибка: " + e);
}
