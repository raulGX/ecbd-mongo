require("babel-core/register");
const Koa = require("koa");
const app = new Koa();
const mongo = require("koa-mongo");
// x-response-time

app.use(mongo());
app.use(async (ctx, next) => {
  const result = await ctx.mongo
    .db("ecbd")
    .collection("users")
    .insert({ name: "haha" });
  const userId = result.ops[0]._id.toString();
  ctx.body = await ctx.mongo
    .db("ecbd")
    .collection("users")
    .find()
    .toArray();
  ctx.mongo
    .db("ecbd")
    .collection("users")
    .remove({
      _id: mongo.ObjectId(userId)
    });
});
app.listen(3001, () => {
  console.log("listening on port 3001");
});
